# Order Status Tracker with Kafka + WebSocket

Ứng dụng theo dõi trạng thái đơn hàng sử dụng **Spring Boot + Kafka + WebSocket + Thymeleaf**.

## 🏗️ Kiến trúc

\`\`\`
Admin/System → Kafka Producer → Kafka Topic → Kafka Consumer → WebSocket → Browser
\`\`\`

## ✨ Tính năng

- ✅ **Kafka Integration**: Message queue cho scalability
- ✅ **Real-time Updates**: WebSocket cho instant updates
- ✅ **Multiple Sources**: Admin + System simulation
- ✅ **Event Sourcing**: Tất cả updates qua Kafka
- ✅ **Responsive UI**: Bootstrap + animations
- ✅ **Connection Status**: Real-time connection monitoring

## 🚀 Cách chạy

### 1. Start Kafka với Docker
\`\`\`bash
docker-compose up -d
\`\`\`

### 2. Verify Kafka
- Kafka: http://localhost:9092
- Kafka UI: http://localhost:8090

### 3. Run Spring Boot
\`\`\`bash
mvn spring-boot:run
\`\`\`

### 4. Access Application
- App: http://localhost:8080
- Kafka UI: http://localhost:8090

## 🧪 Test Real-time

1. **Mở 2 tabs**: http://localhost:8080
2. **Tab 1**: Cập nhật từ Admin panel
3. **Tab 2**: Xem real-time updates
4. **Click "Simulate"**: Test system updates

## 📊 Monitor Kafka

- **Kafka UI**: http://localhost:8090
- **Topic**: `order-status-updates`
- **Consumer Group**: `order-tracker-group`

## 🔧 Luồng hoạt động

1. **Admin cập nhật** → Kafka Producer
2. **Kafka Topic** → Store message
3. **Kafka Consumer** → Consume message
4. **WebSocket** → Broadcast to all clients
5. **Browser** → Real-time UI update

## 🎯 Lợi ích Kafka + WebSocket

- **Scalability**: Kafka handle millions messages
- **Reliability**: Message persistence
- **Decoupling**: Loose coupling between services
- **Real-time**: Instant UI updates
- **Multiple consumers**: Easy to add more services
