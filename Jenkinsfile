final String BuildPropertiesFile = 'build.properties'

node {       
	
	stage ('Build') {		
		cleanWs()
		
		git branch: 'branch11-1', url: 'https://github.com/dachyut/MavenProject.git'
		
		echo "******** I am in branch11-1********"
		echo "BRANCH_NAME: ${env.BRANCH_NAME}"
		echo "CHANGE_TARGET: ${env.CHANGE_TARGET}"
		echo "CHANGE_BRANCH: ${env.CHANGE_BRANCH}"
		echo "JOB_BASE_NAME: ${env.JOB_BASE_NAME}"		
	
		println "${JENKINS_URL}"
		println "${env.BRANCH_NAME}"
		
		def commit1 = bat "git rev-parse HEAD"
		println commit1
		def c = "COMMIT=${commit1}"
		println c
		
		sh "git rev-parse HEAD >./commit_id"
		String myCommit = readFile('./commit_id').replaceAll('\\W', '')
		
		bat "echo BRANCH=3fde0df43603023269315c2fa816bed21d5aa360 > build.properties"
		bat "echo COMMIT=$myCommit >> build.properties"		
		bat "echo DCPROTECT_MAC_INSTALLER=win.exe >> build.properties"
		
		//sh 'echo "BRANCH=3fde0df43603023269315c2fa816bed21d5aa360" > build.properties'
		//sh 'echo "COMMIT=${commit}" >> build.properties'
		//sh 'echo "DCPROTECT_MAC_INSTALLER= " >> build.properties'
		archiveArtifacts artifacts: 'build.properties', fingerprint: true
		
		//httpRequest authentication: '669a0175-39d9-487f-92e4-6fbf1723599a', outputFile: 'output.txt', responseHandle: 'NONE', url: "${JENKINS_URL}job/MultiBranchPipeline/job/${env.BRANCH_NAME}/lastSuccessfulBuild/artifact/build.properties"
		
		//String suiteFile = readFile('output.txt')
		// split lines
		//skipComponentsList = (((suiteFile.split('\n')
				// remove blank lines
		//		.findAll { item -> !item.isEmpty() })
				// find line contains '='
		//		.findAll { it.contains('=') })
				// collections of switches
		//		.collectEntries{ [(it.split("=")[0].trim()): it.split("=")[1].trim()] })
		//		.findAll{ it.key == 'COMMIT' }

		//println skipComponentsList.get('COMMIT')
		
	
		/*buildStatus = getCIBuild(env.BRANCH_NAME,buildPropertiesFile)
		println "${env.BRANCH_NAME} build: ${buildStatus}"
		println "echo ${buildPropertiesFile}"
		
		buildStatus = getCIBuild(env.CHANGE_BRANCH,buildPropertiesFile)
		println "${env.CHANGE_BRANCH} build: ${buildStatus}"
		println "echo ${buildPropertiesFile}"*/
	}	
		
}

Boolean getCIBuild(targetBranch, buildPropertiesFile) {
    final String commitKey = 'COMMIT'
    final String artifactKey = 'DOWNLOAD_URL'
    final String targetCIJob =  '//MultiBranchPipeline/' + targetBranch

    try {
        step([$class: 'CopyArtifact',
            filter: "${buildPropertiesFile}",
            fingerprintArtifacts: true,
            flatten: true,
            selector: lastSuccessful(),
            projectName: targetCIJob])
    } catch (Exception e) {
        println "Could not find last successful build properties for job:  ${targetCIJob}"
        println e
        return false
    }

    def buildProps = readProperties file:buildPropertiesFile
    if (!buildProps.containsKey(commitKey)) {
        println "Could not find what commit was used in the last successful CI build of target ${targetCIJob}."
        return false
    }

    // Verify existence of the artifacts download location
    if (!buildProps.containsKey(artifactKey)) {
        println "Could not find the artifacts location for the last successful CI build of target ${targetCIJob}."
        return false
    }
    String artifactsCmd = 'curl --head --fail ' + buildProps[artifactKey]
    def myStatus = sh(returnStatus: true, script: artifactsCmd)
    if (myStatus != 0) {
        println "Artifacts location does not exist or is unreachable for the last successful CI build of target ${targetCIJob}: ${buildProps[artifactKey]}"
        return false
    }

    if (buildProps[commitKey] == getCommitHash("origin/${targetBranch}")) {
        println "The last successful CI build ${targetCIJob} is up to date."
    } else {
        String[] changedFiles = getChangedFiles(buildProps[commitKey], "origin/${targetBranch}")
        if (!isOnlyAutomation(changedFiles)) {
            println "Target branch ${targetBranch} has non-automation commits not included in the last successful CI build ${targetCIJob}."
            return false
        }
    }

    sh "mv ${buildPropertiesFile} ${env.WORKSPACE}"
    return true
}

@NonCPS
def getLastSuccessfulCommitId(build) {
	def lastSuccBuild
    def changeSets
    def lastSuccCommitId   
    currBuild = build
	println "====> Current build: ${currBuild}"
	println currBuild.getClass()
    while (currBuild?.getPreviousBuild()?.result !=null) {
        currBuild = currBuild.getPreviousBuild()
        if(currBuild.result == 'SUCCESS' && (currBuild.changeSets).size() > 0) {
            lastSuccBuild = currBuild
			println "Last successful Build_Current number: ${lastSuccBuild.number} Build Result: ${lastSuccBuild.result}"
            break
        }
    }
    
	println "Looking for successful build in Target branch ${env.CHANGE_TARGET}"
	//def buildNumber = Jenkins.instance.getItem(env.CHANGE_TARGET)
	def build_job = build job: "${env.CHANGE_TARGET}"
	println build_job.getClass()
	//build_job_number = build_job.getNumber()
	//println build_job_number
	/***currBuild = env.CHANGE_TARGET
	while (currBuild?.getPreviousBuild()?.result !=null) {
		currBuild = currBuild.getPreviousBuild()
		if(currBuild.result == 'SUCCESS' && (currBuild.changeSets).size() > 0) {
			lastSuccBuild = currBuild
			println "Last successful Build_Target number: ${lastSuccBuild.number} Build Result: ${lastSuccBuild.result}"
			break
		}
	}****/
}

//commit1
//commits in branch11-1
//
//
//
//
//
//
