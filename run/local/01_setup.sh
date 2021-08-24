docker network create spring-cloud_net

docker run -d --rm \
      --name maria \
      --net spring-cloud_net \
      -p 3306:3306 \
      -v ~/dev/data/maria/:/data/db \
      -e MYSQL_ROOT_PASSWORD=mariadb \
      mariadb

docker run -d --rm \
      --name mongo \
      --net spring-cloud_net \
      -p 27017:27017 \
      -v ~/dev/data/mongo/:/data/db \
      mongo