#/bin/bash

#uso: ./start-local.sh <local|dev|qa|pci> jvm(local) <-D outros parametros>
#org.gradle.java.home=/usr/local/sdkman/candidates/java/11.0.12-open

echo "Copiando certificado"
sudo mkdir -p /opt/certs
sudo cp certs/hml/keystore.jks /opt/certs/keystore.jks

echo "Build application"
./gradlew ktlintFormat -Dorg.gradle.java.home="$2" clean quarkusDev \
    -Dquarkus.datasource.username=AKI \
    -Dquarkus.datasource.password='AKI!CH' \
    -Dquarkus.profile="$1" \
    -Dquarkus.sqs.aws.credentials.static-provider.access-key-id=AKI \
    -Dquarkus.sqs.aws.credentials.static-provider.secret-access-key='Jiiub' \
    -Dapp.rsa.key.cipher='RSA/ECB/PKCS1PADDING' \
    -Ddatafortress.access_id='AKI' \
    -Ddatafortress.secret_key='AKI' \
    -Dapisecurity.clientside.hash.algorithm='HmacSHA256' \
    -Dreversal.transaction.message.limit='10'
