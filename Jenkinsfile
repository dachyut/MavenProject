pipeline {
    
    agent any
    tools {
        maven 'maven'
    }
    
    stages {
        stage ('General') {
            steps {
                echo "Current branch name: ${env.BRANCH_NAME}"
                sh 'echo "artifact file" > generatedFile.txt'
                skipBuild = getLastSuccessfulBuild()
                echo "LSB: ${skipBuild}"
            }
        }        
	}

    post {
        always {
            archiveArtifacts artifacts: 'generatedFile.txt', fingerprint: true
        }
    }
}

/***********************
    Get artifacts from last successfull build
************************/
Boolean getLastSuccessfulBuild() {
    final String commitKey = 'COMMIT'
    final String artifactKey = 'DOWNLOAD_URL'
    final String targetCIJob = 'MultiBranchPipeline/newBranch'

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
}

