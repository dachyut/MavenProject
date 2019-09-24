node {        
	stage ('Build') {		
		cleanWs()
		echo "******** I am in branch11-1********"
		echo "BRANCH_NAME: ${env.BRANCH_NAME}"
		echo "CHANGE_TARGET: ${env.CHANGE_TARGET}"
		echo "CHANGE_BRANCH: ${env.CHANGE_BRANCH}"
		echo "JOB_BASE_NAME: ${env.JOB_BASE_NAME}"		
		
		currBuild = currentBuild
		while (currBuild?.getPreviousBuild()?.result !=null) {
			currBuild = currBuild.getPreviousBuild()
			if(currBuild.result == 'SUCCESS' && (currBuild.changeSets).size() > 0) {
				lastSuccBuild = currBuild
				println "LSB of Current: ${lastSuccBuild}"
				break
			}
		}
		
		currBuild = env.CHANGE_TARGET
        while (currBuild?.result !=null) {
            currBuild = currBuild?.getPreviousBuild()?
            if(currBuild.result == 'SUCCESS' && (currBuild.changeSets).size() > 0) {
                lastSuccBuild = currBuild
				println "LSB of Target: ${lastSuccBuild}"
                break
            }
        }
		
	}	
}

//commit1
//commits in branch11-1