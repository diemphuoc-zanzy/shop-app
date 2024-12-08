# Chọn image Java từ Docker Hub
FROM maven:3.8-openjdk-17 AS builder

# Copy mã nguồn dự án vào container
WORKDIR /app

# Sao chép file pom.xml trước
COPY pom.xml /app/

# Tải và cache các dependencies
RUN mvn dependency:resolve

# Sao chép toàn bộ mã nguồn vào container
COPY . /app/

# Build ứng dụng bằng Maven
RUN mvn clean package

# Chọn base image cho runtime
FROM openjdk:17-jdk-slim

# Copy file JAR từ builder stage
COPY --from=builder /app/target/shopapp-0.0.1-SNAPSHOT.jar /app/shop-app.jar

# Chỉ định cổng chạy ứng dụng
EXPOSE 8088

# Them bien moi truong
ENV SPRING_PROFILES_ACTIVE=dev

# Chạy ứng dụng Java
CMD ["java", "-jar", "/app/shop-app.jar"]

