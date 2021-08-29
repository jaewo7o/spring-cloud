cd ../..

projectHome=$(pwd)

cd $projectHome
./gradlew build -x test

cd $projectHome/services/composite
docker build -t composite -f ./Dockerfile .

cd $projectHome/services/product
docker build -t product -f ./Dockerfile .

cd $projectHome/services/recommend
docker build -t recommend -f ./Dockerfile .

cd $projectHome/services/review
docker build -t review -f ./Dockerfile .