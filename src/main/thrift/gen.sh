thrift --gen java songservice.thrift

cp ./gen-java/com ../java/ -rf
rm -r gen-java