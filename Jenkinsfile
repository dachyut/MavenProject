pipeline {
    
    agent any
    tools {
        maven 'maven'
    }
    
    stages {
        stage ('Build') {
            
            steps {
				echo 'Build stage...'
				echo "******** I am in master ********"
				echo "BRANCH_NAME: ${env.BRANCH_NAME}"
				echo "CHANGE_TARGET: ${env.CHANGE_TARGET}"
				echo "CHANGE_BRANCH: ${env.CHANGE_BRANCH}"
				echo "JOB_BASE_NAME: ${env.JOB_BASE_NAME}"	
				
				echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
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
				bat "java -jar src/main/java/code/myorg/Calculator/MyCalculator.jar"
			}
		}		
	}
}
