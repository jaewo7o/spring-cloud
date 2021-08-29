curl --header "Content-Type: application/json" \
    --request POST \
    --data '{"productId":1, "productName":"1_name", "productInfo":"1_productInfo", "recommends":[{"recommendId":91, "author":"91_author", "content":"91_content"}], "reviews": [{"reviewId":81, "author":"81_author", "subject":"81_subject", "content":"81_content"}]}' \
http://localhost:7000/composite

curl http://localhost:7000/composite/1 | jq

curl -X "DELETE" http://localhost:7000/composite/1
