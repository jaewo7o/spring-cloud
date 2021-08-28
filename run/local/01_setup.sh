docker network create spring-cloud_net

docker run -d --rm \
      --name maria \
      --net spring-cloud_net \
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
      --net spring-cloud_net \
      -p 27017:27017 \
      -v ~/dev/data/mongo/:/data/db \
      mongo