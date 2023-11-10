## Запуск
1) Скачиваем файл `south-fed-district-latest.osm.pbf` с сайта https://download.geofabrik.de/russia/south-fed-district.html
   И кладем в папку resources по адресу "src/main/resources/south-fed-district-latest.osm.pbf"
Примечание: для работы приложения по всей России, нужно скачать карту всей России.  
Можно также ограничиться нужным вам регионом.
## Запуск с traefik
1) распаковываем контейнеры на разных машинах: frontend, apiLoadBalancer, api1, api2, ..., apin, db.  
    Количество frontend серверов тоже можно размножить  
    1 контейнер - 1 машина.  
    Архитектуру production решения можно изменять в зависимости от ситуации 
2) указываем нужные ip

Для тестирования можно оставить монолит
Для тестирования можно использовать комманду:  
   docker compose -f docker-compose.example-api.yaml up -d --scale whoami=3 

traefik слушает все ip, у которых порт 80 и транслирует на 80 порту
api соединяется с traefik с помощью hostname. В данном случае 127.0.0.1. 
Может быть указан зарегестрированный официальный домен или локальный домен.
В api наружу выставляется рандомный ip и порт 80.
В примере 127.0.0.1:80 (traefik) -> x.x.x.x:80 (api instance x),  
x - целое число

      # для запуска на монолите удобно использовать строку ниже
      # - "traefik.http.routers.whoami.rule=Host(`api.localhost`)"
      # тогда запрос к api через traefik будет api.localhost:80