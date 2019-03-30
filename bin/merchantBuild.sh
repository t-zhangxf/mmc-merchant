#!/bin/sh

echo "package：$1 环境";

if [ ! -n "$1" ] ;then
    echo "环境参数为空"
    exit
fi

cd ../ && mvn clean -P$1 -Dmaven.test.skip=true && mvn compile -P$1 -Dmaven.test.skip=true && mvn package -P$1 -Dmaven.test.skip=true && mvn install -P$1 -Dmaven.test.skip=true

cd pay-mmc-service/pay-merchant-client/target
rm -rf pay-merchant-client-build
mkdir pay-merchant-client-build
cp pay-merchant-client-1.0-SNAPSHOT.jar pay-merchant-client-build
cd pay-merchant-client-build
mkdir awslogs
cp ../../../../bin/awscli.conf awslogs
cp ../../../../bin/awslogs.conf awslogs
mkdir template
cp -r ../classes/template/. template
cd ../
cp ../../../bin/deploy.sh pay-merchant-client-build
tar zcvf root.tar.gz pay-merchant-client-build
echo "package pay-merchant-server done!"

cd ../../../
cd pay-mmc-web/pay-merchant-web-client/target
rm -rf pay-mmc-web-build
mkdir pay-mmc-web-build
cp pay-merchant-web-client-0.0.1-SNAPSHOT.jar pay-mmc-web-build
cd pay-mmc-web-build
mkdir awslogs
cp ../../../../bin/awscli.conf awslogs
cp ../../../../bin/awslogs.conf awslogs
cd ../
cp ../../../bin/deploy.sh pay-mmc-web-build
tar zcvf root.tar.gz pay-mmc-web-build
echo "package pay-mmc-web done!"