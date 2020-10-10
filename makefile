help: ## This help message
	@echo "$$( grep -hE '^\S+:.*##' $(MAKEFILE_LIST) | sed -e 's/##//' )"

up:   ## Start up all containers defined in the docker-compose
	mvn clean compile package
	docker-compose -p garage2 up -d --build
	
down: ## Shut down all containers defined in the docker-compose and remove volumes as well
	docker-compose down -v
	
karate: ## Run karate tests
	cd garage-api-test && rm -fr target && java -jar /opt/karate/karate-0.9.6.jar booking.feature
	