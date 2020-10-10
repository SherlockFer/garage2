function(){
	var config = {}
	
	if (!karate.env || karate.env== 'dev') {
		config.GARAGE_API_URL = 'http://localhost:8080';
	}
	if (karate.env == 'docker'){
		config.GARAGE_API_URL = 'http://api:8080';
	}
	
	return config;
}