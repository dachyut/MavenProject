node {
        stage ('General') {
                cleanWs()
                echo "BRANCH_NAME: ${env.BRANCH_NAME}"
                echo "BUILD_DISPLAY_NAME: ${env.BUILD_DISPLAY_NAME}"
                echo "JOB_NAME: ${env.JOB_NAME}"
                echo "JOB_BASE_NAME: ${env.JOB_BASE_NAME}"
				echo "CHANGE_TARGET: ${env.CHANGE_TARGET}"
				echo "GIT_BRANCH: ${env.GIT_BRANCH}"
				echo "GIT_PREVIOUS_COMMIT: ${env.GIT_PREVIOUS_COMMIT}"
				echo "GIT_PREVIOUS_SUCCESSFUL_COMMIT: ${env.GIT_PREVIOUS_SUCCESSFUL_COMMIT}"
				echo "GIT_COMMIT: ${env.GIT_COMMIT}"
				echo "CHANGE_BRANCH: ${CHANGE_BRANCH}"
				echo "currentBuild: ${currentBuild}"
				
				currentChgSets = getCurrentBuildCommitLog()
				println "Current commit: ${currentChgSets}"
				lastSuccChgSets = getLastSuccessfulBuildCommitLog(currentBuild)
				println "Last successfulcommit: ${lastSuccChgSets}"				
				
				changedFiles = getChangedFiles(currentChgSets, lastSuccChgSets)
				println "Changed files:"
				if (changedFiles != null) {
					for (int ii = 0; ii < changedFiles.size(); ii++) {
						println changedFiles[ii]
					}
				}	
				println "==============================="
				//def currSuccBuild = getLastSuccessfulBuild()
				//println Jenkins.instance.getItem(env.JOB_NAME)
				
				def item = Jenkins.instance.getItem(env.JOB_NAME)
				def  ff=item.getLastSuccessfulBuild()
				println ff
				
				println "================================"
				
                //sh 'echo "artifact file-3" > generatedFile.txt'                
                
                //def skipBuild = getLastSuccessfulBuild()
                //echo "LSB: ${skipBuild}"
                //archiveArtifacts artifacts: 'generatedFile.txt', fingerprint: true
        }  

        stage ('test1') {
            echo "Testing stage1"
            echo "Hello...."
        }

        stage ('test2') {
            echo "Testing stage2"
            echo "Hello...."
            //error('Failing stage')
            
        }      
}

/***********************
    Get artifacts from last successfull build
************************/
Boolean getLastSuccessfulBuild(branch) {
    final String commitKey = 'COMMIT'
    final String artifactKey = 'DOWNLOAD_URL'
    //final String targetCIJob = 'MultiBranchPipeline/PR-2'

    try {
        step([$class: 'CopyArtifact',
            //filter: "generatedFile.txt",
            fingerprintArtifacts: true,
            flatten: true,
            selector: lastSuccessful(),
            projectName: branch])
    } catch (Exception e) {
        println "Could not find last successful build properties for job"
        println e
        return false
    }
    return true
}

/*****************************************
    Get Last successfull build commit log
******************************************/
@NonCPS
def getLastSuccessfulBuildCommitLog(build) {
    def lastSuccBuild
    def changeLogSets
    def lastSuccCommitLog    
    currBuild = build
    while (currBuild?.getPreviousBuild()?.result !=null) {
        currBuild = currBuild.getPreviousBuild()
        if(currBuild.result == 'SUCCESS' && (currBuild.changeSets).size() > 0) {
            lastSuccBuild = currBuild
            break
        }
    }
    if (lastSuccBuild != null) {
        println "Last successful Build number: ${lastSuccBuild.number} Build Result: ${lastSuccBuild.result}"
    }
    else {
        println "There are no Last successful Build"
        return null
    }
    changeLogSets = lastSuccBuild.changeSets    
    if (changeLogSets.size() > 0) {
        def entries = changeLogSets[0].items
        lastSuccCommitLog = entries[entries.length-1].commitId
    }
    println "Last Successful Commit Log: ${lastSuccCommitLog.toString()}"
    //return (lastSuccCommitLog + '^').toString()
    return lastSuccCommitLog.toString()
}

/*********************************
    Get current build commit log
**********************************/
@NonCPS
def getCurrentBuildCommitLog () {
    def changeLogSets = currentBuild.changeSets    
    def currentCommitLog
    if (changeLogSets.size() > 0) {
        def entries = changeLogSets[0].items
        currentCommitLog = entries[entries.length-1].commitId
    }
    println "Current Commit Log: ${currentCommitLog.toString()}"
    return currentCommitLog.toString()
}

def getChangedFiles (firstCommit, secondCommit) {
    final String changedFilesList = 'changed_files.txt'
    def myStatus = sh(returnStatus: true, script: "git diff --name-only ${firstCommit} ${secondCommit} > ${changedFilesList}")
    if (myStatus != 0) {
        println "Git failed getting the list of changed files."
        return null
    }
    sh "wc -l ${changedFilesList}"
    sh "cat ${changedFilesList}"
    return readFile(changedFilesList).split("\n")
}

//
//
//


