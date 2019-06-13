pipeline {
    
    agent any
    tools {
        maven 'maven'
    }
    
    stages {
        stage ('Build') {
            
            steps {
				echo 'Build stage...'
                //git url: 'D:\\Maven Project\\my-maven-project'
            }
        }
		
		stage ('Scripts') {
				steps {
					echo 'Scripts running stage...'
					powershell "PSscript.ps1"
				}
		}
    
        stage ('Test') {
            steps {
                echo 'Testing stage....'
                bat "mvn test"
			}
		}		
	}
}
