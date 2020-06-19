import io.gatling.core.Predef._
import io.gatling.http.Predef._

class RestSimulation extends Simulation {
 
    //val httpProtocol = http.baseUrl("http://localhost:8080")
    val httpProtocol = http.baseUrl("http://192.168.99.100").header("Host", "kafka-demo.local")
 
    val scn = scenario("RestSimulation")
      .exec(
          http("GET Customers").get("/messages")
      )
      .exec(
          http("POST customers").post("/messages").header("Content-Type", "application/json")
          .body(StringBody("""{ "name": "test name", "address": "test address" }""")).asJson
      )
 
    setUp(scn.inject(atOnceUsers(100))).protocols(httpProtocol)
}