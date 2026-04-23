🚀 Backend Engineering Assignment – Core API & Guardrails
📌 Overview

This project is a Spring Boot-based backend system designed to handle high-concurrency interactions using Redis as a real-time guardrail system.

It includes:

Post & Comment APIs
Redis-based Virality Engine
Atomic Locks for concurrency control
Notification batching system
🧱 Tech Stack
Java 17
Spring Boot 3
PostgreSQL
Redis
Docker
⚡ Features
1. Virality Engine (Redis)
Bot Reply → +1
Like → +20
Comment → +50
Implemented using Redis INCR for atomic updates
2. Atomic Locks (Concurrency Control)
Horizontal Cap:
Max 100 bot replies per post
→ Redis INCR used for atomic counting
Cooldown Cap:
A bot cannot interact with the same user within 10 minutes
→ Redis TTL-based key
Vertical Cap:
Comment depth limited to 20 levels
3. Notification Engine (Smart Batching)
Immediate notification if no recent activity
Otherwise stored in Redis list
Cron job summarizes notifications every 5 minutes
🧠 Thread Safety Approach

Redis atomic operations (INCR, SET with TTL) were used to ensure thread-safe operations under high concurrency.

This prevents race conditions when multiple requests are processed simultaneously.

🧪 Testing Strategy
Tested using Postman
Simulated multiple bot requests
Verified Redis keys using CLI
Ensured correct blocking after limits
▶️ How to Run
# Run Redis
docker run -d -p 6379:6379 redis

# Run PostgreSQL
docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=1234 postgres

Then run Spring Boot application.
