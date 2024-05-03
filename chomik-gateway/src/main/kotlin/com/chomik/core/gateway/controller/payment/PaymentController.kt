package com.chomik.core.gateway.controller.payment

import com.chomik.core.gateway.domain.User
import com.chomik.core.gateway.service.AuthorizationUserDetailsService
import com.chomik.delivery.client.DeliveryClient
import com.chomik.delivery.client.dto.CreateShipmentRequest
import com.chomik.orders.client.OrderClient
import com.chomik.orders.client.dto.OrderDto
import com.chomik.payment.client.PaymentClient
import com.chomik.payment.client.dto.CreatePaymentRequest
import com.chomik.payment.client.dto.CreatePaymentResponse
import com.chomik.storage.client.AdvertClient
import com.chomik.storage.client.dto.AdvertDto
import com.chomik.storage.client.dto.UpdateSneakersCountRequest
import com.payment.mock.model.ProcessedTransactionResponse
import com.payment.mock.model.TransactionStatus

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/payment")
class PaymentController(
    private val deliveryClient: DeliveryClient,
    private val orderClient: OrderClient,
    private val paymentClient: PaymentClient,
    private val advertClient: AdvertClient,
    private val userService: AuthorizationUserDetailsService
) {
    @PostMapping
    fun createPayment(@RequestBody createPaymentRequest: CreatePaymentRequest, authentication: Authentication): ResponseEntity<CreatePaymentResponse> {
        deliveryClient.getUserAddress(createPaymentRequest.addressId, authentication.name)
        orderClient.updateOrderUserAddressTo(createPaymentRequest.orderId, createPaymentRequest.addressId)
        orderClient.updateOrderPaymentCreate(createPaymentRequest.orderId)
        return paymentClient.createPayment(createPaymentRequest)
    }

    @PostMapping("/callback")
    fun processBankCallback(@RequestBody processedTransactionResponse: ProcessedTransactionResponse) {
        //меняем статус оплаты
        val paymentDto = paymentClient.processBankCallback(processedTransactionResponse).body!!

        if (processedTransactionResponse.status.equals(TransactionStatus.SUCCESS)) {
            // меняем статус заказа
            val orderDto: OrderDto = orderClient.updateOrderPaymentFinish(paymentDto.orderId).body!!
            // уменьшаем количество кросовки в объявлении
            val advertDto: AdvertDto = advertClient.updateSneakersCountInAdvert(orderDto.advertId, UpdateSneakersCountRequest(orderDto.sneakerCount)).body!!

            val buyerId: String = orderDto.buyerId
            val sellerId: String = advertDto.sellerId

            val addressFrom : String = advertDto.sellerAddressId
            val addressTo: String = orderDto.userAddressTo!!

            val buyer: User = userService.findById(buyerId)
            val seller: User = userService.findById(sellerId)

            // отправляем запрос на доставку
            deliveryClient.createShipment(CreateShipmentRequest(
                orderId = orderDto.id,
                userAddressFrom = addressFrom,
                userAddressTo = addressTo,
                seller.phoneNumber!!,
                buyer.phoneNumber!!))
        }

    }
}
