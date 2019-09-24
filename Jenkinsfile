node {        
	stage ('Build') {		
		cleanWs()
		echo "******** I am in branch11-1********"
		echo "BRANCH_NAME: ${env.BRANCH_NAME}"
		echo "CHANGE_TARGET: ${env.CHANGE_TARGET}"
		echo "CHANGE_BRANCH: ${env.CHANGE_BRANCH}"
		echo "JOB_BASE_NAME: ${env.JOB_BASE_NAME}"		
		
		currBranch = env.CHANGE_BRANCH
		while (currBranch != null) {
			println "****** Current Branch: ${currBranch}"
			currBranch = env.CHANGE_TARGET
		}
	}	
}

//commit1
//commits in branch11-1