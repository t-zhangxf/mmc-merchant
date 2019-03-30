#!/bin/sh

ENV=$1
PRJ=$2
VERSION=$3

if [ ! -n "$ENV" ] ;then
    echo "Must specify an environment: [dev, prod]!"
    exit
fi
echo "Environment：$ENV";

if [ ! -n "$PRJ" ] ;then
    echo "Must specify a project name!"
    exit
fi
echo "Build Project: $PRJ";

cd ../ && mvn clean install -P$ENV -pl $PRJ -Dmaven.test.skip=true

if [ -d "$PRJ/target" ] ;then
    echo "Package Project: $PRJ"

    cd $PRJ/target

    rm -rf $PRJ-build
    mkdir $PRJ-build
    cp $PRJ-1.0-SNAPSHOT.jar $PRJ-build/

    cd $PRJ-build

    mkdir awslogs
    cp ../../../bin/awscli.conf awslogs/
    sed -e "s/{env}/$ENV/g" -e "s/{project_name}/$PRJ/g" ../../../bin/awslogs.conf > awslogs/awslogs.conf

    cd ../

    cp ../../bin/deploy.sh $PRJ-build/

    tar zcvf root.tar.gz $PRJ-build

    if [ -n "$VERSION" ] ;then
        mv root.tar.gz root.tar.gz_$VERSION
    fi

    echo "Package $PRJ done!"
fi