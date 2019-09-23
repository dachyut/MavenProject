pipeline {
    
    agent any
    tools {
        maven 'maven'
    }
    
    stages {
        stage ('Build') {
            
            steps {
				echo 'Build stage...'
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

//commit1