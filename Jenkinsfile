pipeline {
    
    agent any
    tools {
        maven 'maven'
    }
    
    stages {
        stage ('General') {
            steps {
                echo "Current branch name: ${env.BRANCH_NAME}"
                sh 'echo "artifact file" > generatedFile.txt'
            }
        }        
	}

    post {
        always {
            archiveArtifacts artifacts: 'generatedFile.txt', fingerprint: true
        }
    }
}

