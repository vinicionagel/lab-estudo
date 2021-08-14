# API

## Requisitos
* Java 11+
* Maven 3.6.3+
* PostgreSQL 10+

## Gerando um arquivo JKS com um par de chaves
```
keytool -genkeypair -alias api -keyalg RSA -keypass api#2021 -keystore api.jks -storepass 123456 -validity 365
```
## Listando as entradas de um arquivo JKS
```
keytool -list -keystore api.jks
```
## Gerando o certificado
```
keytool -export -rfc -alias api -keystore api.jks -file api-cert.pem
```
## Gerando a chave pública
```
openssl x509 -pubkey -noout -in api-cert.pem > api-pkey.pem
``` 

## Para testes de integração rodar o seguinte comando:

```
mvn -B failsafe:integration-test --file api/pom.xml
```

## Para testes unitários

```
mvn -B test --file api/pom.xml
```
