help: ## This help message
	@echo "$$( grep -hE '^\S+:.*##' $(MAKEFILE_LIST) | sed -e 's/##//' )"
	
karate: ## Run karate tests
	java -jar /opt/karate/karate-0.9.6.jar --clean booking.feature user.feature part.feature product.feature