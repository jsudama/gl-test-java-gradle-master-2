  
  
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                bat 'gradlew build'
            }
        }
		stage('SCA') {
			steps {
				powershell """
                  Set-ExecutionPolicy AllSigned -Scope Process -Force
                  iex ((New-Object System.Net.WebClient).DownloadString('https://download.srcclr.com/ci.ps1')) 
                  srcclr scan .
		        """
        	}
		 }
		stage('Veracode Pipeline Scan') {
            steps {
                powershell """
                  curl  https://downloads.veracode.com/securityscan/pipeline-scan-LATEST.zip -o pipeline-scan.zip
                  Expand-Archive -Force -Path pipeline-scan.zip -DestinationPath veracode_scanner
                  java -jar veracode_scanner\\pipeline-scan.jar --veracode_api_id '${VERACODE_API_ID}' \
                  --veracode_api_key '${VERACODE_API_KEY}' \
                  --file target/*.war
                """
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

