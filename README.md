## Description

Imagine transforming traditional farming into a harmonious blend of time-honored wisdom and modern innovation. Smart agriculture brings farmers and technology together, helping them better understand and care for their land. By thoughtfully monitoring soil health, weather patterns, and growing conditions, this system becomes a helpful partner in farming - sending timely alerts when crops and livestock need attention and presenting clear, meaningful insights that help farmers make informed decisions. The goal is to support farmers in nurturing their crops and livestock more effectively.

![[Grafana Dashboard.png]]
## Architecture

![[Architecture.png]]


## Getting Started

### Prerequisites

Here are things you need to have on your computer beforehand.

* Docker

### Installation


1. Clone the repo
   ```sh
   git clone https://github.com/Ahsan-Aziz-Ishan/Smart-Agriculture
   ```
2. Run the containers
   ```sh
   docker-compose up -d
   ```
3. Navigate to http://localhost:3000/, where you can see the dashboard. Use the following credentials: username=admin, password=prom-operator.
4. Navigate to http://localhost:1880/ui, where you can see the Sensor Configuration page. You can get your chat-id by messaging @get_id_bot in Telegram. This input accepts comma separated values without space.

![[Alert Configuration.png]]
## Configuration

The configuration of the system is mainly contained in the docker-compose.yml file. Be sure that all the exposed mapped ports are free on your environment:

* 1883 and 9001 for Mosquitto MQTT Broker
* 8086 for InfluxDB
* 1886 for Node-RED
### Node-RED flow

![[Node-RED Flow.png]]

To interact with InfluxDB, navigate to http://localhost:8086/. Use the following credentials: username=admin, password=admin1234!.

## Troubleshooting

* If Grafana fails to start, try executing
`chmod -R a+w .` or `sudo chmod -R a+w .` (if previous fails) inside the repository
## Developed by
* Muhammad Shayan
* Victor Moboluwarin Olasehinde
* Ahsan Aziz Ishan

