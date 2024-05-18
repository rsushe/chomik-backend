upstream orders {
    server chomik-orders-1:17003;
    server chomik-orders-2:17003;
}

server {
    listen 80;

    location / {
        proxy_pass http://orders;
    }
}
