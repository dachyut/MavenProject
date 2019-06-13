pipeline {
    
    agent any
    tools {
        maven 'maven'
    }
    
    stages {
        stage ('Build') {
            
            steps {
                git url: 'D:\\Maven Project\\my-maven-project'
            }
        }
    
        stage ('Test') {
            steps {
                
                bat "mvn test"
            }    
        }
    }
    
}