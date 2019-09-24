node {        
	stage ('Build') {		
		cleanWs()
		echo "******** I am in branch11-1********"
		echo "BRANCH_NAME: ${env.BRANCH_NAME}"
		echo "CHANGE_TARGET: ${env.CHANGE_TARGET}"
		echo "CHANGE_BRANCH: ${env.CHANGE_BRANCH}"
		echo "JOB_BASE_NAME: ${env.JOB_BASE_NAME}"		
		
		currBranch = env.BRANCH_NAME
		currBranch_Parent = env.CHANGE_BRANCH
		currBranch_Target = env.CHANGE_TARGET
		while (currBranch != null) {
			println "****** Current Branch: ${currBranch}"
			println "****** Its parent Branch: ${currBranch_Parent}"	
			println "****** Its target Branch: ${currBranch_Target}"

			env.BRANCH_NAME = env.CHANGE_BRANCH
			env.CHANGE_BRANCH = env.CHANGE_TARGET
			
			
			currBranch = env.BRANCH_NAME
			currBranch_Parent = env.CHANGE_BRANCH
			currBranch_Target = env.CHANGE_TARGET
			
		}
	}	
}

//commit1
//commits in branch11-1