#!/bin/sh

java -cp app:app/lib/* com.demo.todos.TodosApplication \
          -Dspring.datasource.url=${SPRING_DATASOURCE_URL} \
          -Dspring.datasource.username=${SPRING_DATASOURCE_USERNAME} \
          -Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
