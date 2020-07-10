  
  
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                bat 'gradlew build'
            }
        }
	    		stage('Veracode Upload and Scan') {
            steps {
                veracode applicationName: 'Build System Demo Application', createSandbox: true, criticality: 'VeryHigh', fileNamePattern: '', replacementPattern: '', sandboxName: 'Sample Dev Sandbox', scanExcludesPattern: '', scanIncludesPattern: '', scanName: 'Test Scan', teams: '', timeout: 60, uploadExcludesPattern: '', uploadIncludesPattern: '**/**.jar', useIDkey: true, vid: '08ddce4e64fb9a6a2cf0d2569e304c4a', vkey: '1b0d280b5d691463ade81c4d90ff6c93a4f0edefcc9f84a7deebc9f57050b27c45016bba5c63669d10ee79d9c5da84144674e2ebbab979e72421fa2b564ab0ab', vpassword: '', vuser: ''
			}
		}
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
	post {
	    always {
	      archiveArtifacts artifacts: 'results.json', fingerprint: true
	    }
	}
}

