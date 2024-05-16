upstream storage {
    server chomik-storage-1:17001;
    server chomik-storage-2:17001;
}

server {
    listen 80;

    location / {
        proxy_pass http://storage;
    }
}
