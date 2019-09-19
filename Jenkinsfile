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
				echo "Base Branch: ${GIT_BRANCH}"
				echo "CHANGE_BRANCH: ${CHANGE_BRANCH}"
				
                //sh 'echo "artifact file-3" > generatedFile.txt'                
                
                //def skipBuild = getLastSuccessfulBuild()
                //echo "LSB: ${skipBuild}"
                archiveArtifacts artifacts: 'generatedFile.txt', fingerprint: true
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
Boolean getLastSuccessfulBuild() {
    final String commitKey = 'COMMIT'
    final String artifactKey = 'DOWNLOAD_URL'
    final String targetCIJob = 'MultiBranchPipeline/PR-2'

    try {
        step([$class: 'CopyArtifact',
            filter: "generatedFile.txt",
            fingerprintArtifacts: true,
            flatten: true,
            selector: lastSuccessful(),
            projectName: targetCIJob])
    } catch (Exception e) {
        println "Could not find last successful build properties for job"
        println e
        return false
    }
    return true
}

