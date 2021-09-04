sudo docker stop eureka;

sudo docker stop composite;

sudo docker stop product;

sudo docker stop recommend;

sudo docker stop review;

sudo docker stop mongo;

sudo docker stop mariadb;


sudo docker rmi eureka;

sudo docker rmi composite;

sudo docker rmi product;

sudo docker rmi recommend;

sudo docker rmi review;

#sudo docker rmi mongo;
#
#sudo docker rmi mariadb;

sudo docker network rm spring-cloud_net;