# 1. Берем за основу "легкую" версию Java 21
FROM eclipse-temurin:21-jdk-alpine

# 2. Создаем папку внутри контейнера
WORKDIR /app

# 3. Копируем туда наш скомпилированный файл (jar)
# Внимание: Сначала мы должны будем собрать проект через Maven!
COPY target/*.jar app.jar

# 4. Говорим, что программа слушает порт 8080
EXPOSE 8080

# 5. Команда для запуска: java -jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]