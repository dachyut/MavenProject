node {       
	
	stage ('Build') {		
		cleanWs()
		echo "******** I am in branch11-1********"
		echo "BRANCH_NAME: ${env.BRANCH_NAME}"
		echo "CHANGE_TARGET: ${env.CHANGE_TARGET}"
		echo "CHANGE_BRANCH: ${env.CHANGE_BRANCH}"
		echo "JOB_BASE_NAME: ${env.JOB_BASE_NAME}"		
	
		
		sh 'echo "BRANCH=3fde0df43603023269315c2fa816bed21d5aa360" > generatedFile.txt'
		sh 'echo "COMMIT=3fde0df43603023269315c2fa816bed21d5aa360" >> generatedFile.txt'
		sh 'echo "DOWNLOAD_URL=http://artifacts.carb.lab/EndpointMidmarket/Shared/Midmarket-New-Build_Hermes/PR/450" >> generatedFile.txt'
		sh 'echo "VAULT_INSTALLER=./Public/Vault/VaultInstaller-10.4.450.450-Carbonite.EXE" >> generatedFile.txt'
		sh 'echo "VAULT_DBHELPER=./Private/Automation/VaultDBHelper-10.4.450.450.zip" >> generatedFile.txt'
		sh 'echo "QUICKCACHE_INSTALLER= " >> generatedFile.txt'
		sh 'echo "DCPROTECT_WIN_INSTALLER=http://artifacts.carb.lab/EndpointMidmarket/Releases/Releasejobs/v10.3.0/Midmarket-New-Build/RC/73.SAVE/Public/Client-NoPassphrase/DCProtect-10.3.2.73-Carbonite.msi" >> generatedFile.txt' 
		sh 'echo "DCPROTECT_WIN_RESTWRAPPER=./Private/Automation/ProtectionServiceRESTWrapper-10.4.450.450.zip" >> generatedFile.txt' 
		sh 'echo "DCPROTECT_MAC_INSTALLER= " >> generatedFile.txt'
           
        archiveArtifacts artifacts: 'generatedFile.txt', fingerprint: true
		
		httpRequest authentication: '669a0175-39d9-487f-92e4-6fbf1723599a', outputFile: 'output.txt', responseHandle: 'NONE', url: 'http://localhost:8080/job/MultiBranchPipeline/job/PR-4/api/json/lastSuccessfulBuild/api/json'

		
		def props = readJSON file: './output.txt'
		println props
	
		//buildStatus = getCIBuild(env.BRANCH_NAME)
		//println "${env.BRANCH_NAME} build: ${buildStatus}"
		
		//buildStatus = getCIBuild(env.CHANGE_BRANCH)
		//println "${env.CHANGE_BRANCH} build: ${buildStatus}"
			
	}	
		
}

Boolean getCIBuild(targetBranch) {
    final String commitKey = 'COMMIT'
    final String artifactKey = 'DOWNLOAD_URL'
    final String targetCIJob =  '//MultiBranchPipeline/' + targetBranch

    try {
        step([$class: 'CopyArtifact',
            filter: "generatedFile.txt",
            fingerprintArtifacts: true,
            flatten: true,
            selector: lastSuccessful(),
            projectName: targetCIJob])
    } catch (Exception e) {
        println "Could not find last successful build properties for job:  ${targetCIJob}"
        println e
        return false
    }
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
