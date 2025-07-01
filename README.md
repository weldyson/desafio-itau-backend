
  <h1>Desafio-Itau-backend</h1>
  <p>REST API in <strong>Java 17</strong> with <strong>Spring Boot</strong> for transaction submission and real-time statistics.</p>
  <hr>

  <h2>🔍 Description</h2>
  <ul>
    <li><code>POST /transaction</code>: submit a transaction (amount, timestamp)</li>
    <li><code>GET /statistics</code>: get stats for the past 60 seconds</li>
    <li><code>DELETE /transaction</code>: clear all stored transactions</li>
    <li>All data is stored <strong>in-memory</strong>, without any persistent database</li>
  </ul>

  <hr>
  <h2>📦 Data Models</h2>

  <pre><code>// src/main/java/com/yourapp/model/Transaction.java
package com.yourapp.model;

import java.time.OffsetDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class Transaction {
    @NotNull
    @PositiveOrZero
    private Double amount;

    @NotNull
    private OffsetDateTime timestamp;

    // getters and setters
}
</code></pre>

  <pre><code>// src/main/java/com/yourapp/model/Statistics.java
package com.yourapp.model;

public class Statistics {
    private long count;
    private double sum;
    private double avg;
    private double min;
    private double max;

    // constructor, getters, setters
}
</code></pre>

  <hr>
  <h2>🎯 API Endpoints</h2>

  <h3>POST /transaction</h3>
  <pre><code>@PostMapping("/transaction")
public ResponseEntity<Void> add(@Valid @RequestBody Transaction tx) {
    // implementation...
}
</code></pre>
  <p><strong>Validations:</strong></p>
  <ul>
    <li><code>amount >= 0</code></li>
    <li><code>timestamp</code> is ISO 8601 and ≤ current time</li>
  </ul>
  <p><strong>Responses:</strong></p>
  <ul>
    <li><code>201 Created</code> – accepted</li>
    <li><code>422 Unprocessable Entity</code> – validation failed</li>
    <li><code>400 Bad Request</code> – invalid JSON</li>
  </ul>

  <h3>GET /statistics</h3>
  <pre><code>@GetMapping("/statistics")
public ResponseEntity&lt;Statistics&gt; getStats() {
    // implementation...
}
</code></pre>
  <p>Returns statistics (count, sum, avg, min, max) for transactions in the last 60 seconds. If none, all values must be zero.</p>

  <h3>DELETE /transaction</h3>
  <pre><code>@DeleteMapping("/transaction")
public ResponseEntity<Void> clear() {
    // implementation...
}
</code></pre>
  <p>Clears all in-memory transactions — returns <code>200 OK</code>.</p>

  <hr>
  <h2>🧠 Implementation</h2>
  <ul>
    <li>Use a <strong>thread-safe</strong> structure (e.g., <code>ConcurrentLinkedQueue&lt;Transaction&gt;</code>) to store transactions</li>
    <li>Filter by <code>timestamp ≥ now − 60 seconds</code></li>
    <li>Compute stats efficiently using <code>DoubleSummaryStatistics</code></li>
  </ul>

  <hr>
  <h2>🛠️ Setup & Run</h2>
  <pre><code>git clone https://github.com/weldyson/desafio-itau-backend
cd desafio-itau-backend

./mvnw clean package
./mvnw spring-boot:run</code></pre>
  <p>API runs at <code>http://localhost:8080</code></p>

  <hr>
  <h2>✅ Testing</h2>
  <p><strong>Unit tests:</strong> JUnit 5 + Mockito</p>
  <p><strong>Integration tests:</strong> MockMvc</p>
  <pre><code>./mvnw test</code></pre>
  <p><strong>Suggested test cases:</strong></p>
  <ul>
    <li><code>POST</code> with negative amount</li>
    <li><code>POST</code> with future timestamp</li>
    <li><code>GET /statistics</code> when no transactions — expect zeros</li>
    <li>Multiple valid transactions → correct stats</li>
  </ul>

  <hr>
  <h2>📌 Enhancements (optional)</h2>
  <ul>
    <li>Docker containerization (<code>Dockerfile</code>)</li>
    <li>Health endpoint via Spring Boot Actuator</li>
    <li>Logging with SLF4J + Logback</li>
    <li>Configurable statistics window via <code>application.yml</code></li>
    <li>API documentation using Swagger / OpenAPI</li>
  </ul>

  <hr>
  <h2>🛠️ Example Commit History</h2>
  <pre><code>feat: add POST /transaction
feat: add GET /statistics
feat: add DELETE /transaction
test: add both unit and integration tests
chore: add Dockerfile        # optional
docs: configure Swagger      # optional</code></pre>


