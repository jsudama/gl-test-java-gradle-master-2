  
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'

		powershell 'mvn package'
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
