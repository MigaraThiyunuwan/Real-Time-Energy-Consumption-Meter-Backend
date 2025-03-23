#include <WiFi.h>
#include <HTTPClient.h>

// Wi-Fi credentials
const char* ssid = "MyWIFI"; 
const char* password = "migara555"; 

// API URL
const char* serverName = "http://192.168.180.69:8080/api/energy/simulate";

// Sensor pins
const int voltagePin = 34; 
const int currentPin = 35; 

// ADC settings
const float referenceVoltage = 3.3; 
const int resolution = 4095;         

// Sensor calibration factors 
const float voltageFactor = 11.0;    
const float currentFactor = 0.1;     

String generateSensorData() {
    // Read raw analog values from sensors
    int rawVoltage = analogRead(voltagePin);
    int rawCurrent = analogRead(currentPin);

    // Convert raw values to actual voltage and current
    float voltage = (rawVoltage * referenceVoltage / resolution) * voltageFactor; // Scale the voltage
    float current = (rawCurrent * referenceVoltage / resolution) / currentFactor; // Scale the current

    // Create JSON payload
    String jsonPayload = "{";
    jsonPayload += "\"voltage\":" + String(voltage, 2) + ",";
    jsonPayload += "\"current\":" + String(current, 2);
    jsonPayload += "}";
    return jsonPayload;
}

void setup() {
    Serial.begin(115200);

    // Initialize Wi-Fi
    WiFi.begin(ssid, password);
    Serial.println("Connecting to Wi-Fi...");
    while (WiFi.status() != WL_CONNECTED) {
        delay(1000);
        Serial.print(".");
    }
    Serial.println("\nConnected to Wi-Fi");

    // Configure ADC pins
    pinMode(voltagePin, INPUT);
    pinMode(currentPin, INPUT);
}

void loop() {
    if (WiFi.status() == WL_CONNECTED) {
        HTTPClient http;

        // Generate sensor data
        String sensorData = generateSensorData();
        Serial.println("Sending API request with sensor data: " + sensorData);

        // Send POST request
        http.begin(serverName);
        http.addHeader("Content-Type", "application/json"); // Set content type
        int httpResponseCode = http.POST(sensorData);

        if (httpResponseCode > 0) {
            String response = http.getString();
            Serial.println("HTTP Response code: " + String(httpResponseCode));
            Serial.println("Response: " + response);
        } else {
            Serial.println("Error on sending POST: " + String(httpResponseCode));
        }

        http.end(); // Free resources
    } else {
        Serial.println("Wi-Fi not connected");
    }

    delay(1000); // Send a request every 1 second
}
