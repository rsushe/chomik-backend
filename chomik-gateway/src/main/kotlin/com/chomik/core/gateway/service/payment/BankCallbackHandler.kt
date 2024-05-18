package com.chomik.core.gateway.service.payment

import com.chomik.core.gateway.domain.User
import com.chomik.core.gateway.service.AuthorizationUserDetailsService
import com.chomik.delivery.client.DeliveryClient
import com.chomik.delivery.client.dto.CreateShipmentRequest
import com.chomik.orders.client.OrderClient
import com.chomik.orders.client.dto.OrderDto
import com.chomik.payment.client.dto.PaymentDto
import com.chomik.payment.client.dto.PaymentStatus
import com.chomik.storage.client.AdvertClient
import com.chomik.storage.client.dto.AdvertDto
import com.chomik.storage.client.dto.UpdateSneakersCountRequest
import com.payment.mock.model.ProcessTransactionResponse
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class BankCallbackHandler(
    private val deliveryClient: DeliveryClient,
    private val orderClient: OrderClient,
    private val advertClient: AdvertClient,
    private val userService: AuthorizationUserDetailsService,
    private val bankCallbackMessageProducer: BankCallbackMessageProducer
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun handleTransactionResponse(processTransactionResponse: ProcessTransactionResponse) =
        bankCallbackMessageProducer.produceTransactionResponse(processTransactionResponse)

    fun handlePaymentResponse(paymentDto: PaymentDto) {
        if (paymentDto.status == PaymentStatus.SUCCESS) {
            // меняем статус заказа
            val orderDto: OrderDto = orderClient.updateOrderPaymentFinish(paymentDto.orderId).body!!
            log.info("updateOrderPaymentFinish")

            // уменьшаем количество кросовки в объявлении
            val advertDto: AdvertDto = advertClient.updateSneakersCountInAdvert(
                orderDto.advertId,
                UpdateSneakersCountRequest(orderDto.sneakerCount)
            ).body!!
            log.info("updateSneakersCountInAdvert")

            val buyerId: String = orderDto.buyerId
            val sellerId: String = advertDto.sellerId

            val addressFrom : String = advertDto.sellerAddressId
            val addressTo: String = orderDto.userAddressTo!!

            val buyer: User = userService.findById(buyerId).user
            val seller: User = userService.findById(sellerId).user

            log.info("successfully get user ids")

            // отправляем запрос на доставку
            val shipment = deliveryClient.createShipment(CreateShipmentRequest(
                orderId = orderDto.id,
                userAddressFrom = addressFrom,
                userAddressTo = addressTo,
                seller.phoneNumber!!,
                buyer.phoneNumber!!)
            )
            log.info("success createShipment {}", shipment.body?.toString())
        }
    }
}
