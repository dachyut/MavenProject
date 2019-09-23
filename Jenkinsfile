pipeline {
    
    agent any
    
    stages {
        stage ('Build') {
            
            steps {
				cleanWs()
				echo "******** I am in branch11 ********"
				echo "BRANCH_NAME: ${env.BRANCH_NAME}"
				echo "CHANGE_TARGET: ${env.CHANGE_TARGET}"
				echo "CHANGE_BRANCH: ${env.CHANGE_BRANCH}"
				echo "JOB_BASE_NAME: ${env.JOB_BASE_NAME}"
				
		
            }
        }				
	}
}

//commit1