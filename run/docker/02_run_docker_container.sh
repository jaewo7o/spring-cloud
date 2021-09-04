docker network create spring-cloud_net

docker run -d --rm \
      --name mariadb \
      --network spring-cloud_net \
      -p 3306:3306 \
      -v ~/dev/data/maria/:/data/db \
      -e TZ=Asia/Seoul \
      -e MYSQL_HOST=localhost \
      -e MYSQL_PORT=3306 \
      -e MYSQL_ROOT_PASSWORD=mariadb \
      -e MYSQL_DATABASE=review-db \
      -e MYSQL_USER=user01 \
      -e MYSQL_PASSWORD=user01 \
      mariadb

docker run -d --rm \
      --name mongo \
      --network spring-cloud_net \
      -p 27017:27017 \
      -v ~/dev/data/mongo/:/data/db \
      mongo

docker run -d --rm --name eureka \
      --network spring-cloud_net \
      -p 8761:8761 \
      eureka

docker run -d --rm --name composite \
      --network spring-cloud_net \
      -p 8080:8080 \
      -e SPRING_PROFILES_ACTIVE=docker \
      composite

docker run -d --rm --name product \
      --network spring-cloud_net \
      -e SPRING_PROFILES_ACTIVE=docker \
      product

docker run -d --rm --name recommend \
      --network spring-cloud_net \
      -e SPRING_PROFILES_ACTIVE=docker \
      recommend

docker run -d --rm --name review \
      --network spring-cloud_net \
      -e SPRING_PROFILES_ACTIVE=docker \
      review