plugins {
    id("jacoco")
}
tasks.withType(Test) {
    jacoco.includeNoLocationClasses = true
}
task jacocoTestReport (type: JacocoReport, dependsOn: [testDebugUnitTest]) {
    reports {
        xml.enabled = true
        html.enabled = true
    }
    def fileFilter =[
            '**/R.class',
            '**/R$*.class',
            '**/BuildConfig.*',
            '**/Manifest*.*',
            '**/*Test*.*',
            'android/**/*.*',
            '**/*$[0-9].*'
    ]
    def debugTree = fileTree (dir: "$project.buildDir/tmp/kotlin-classes/debug", excludes: fileFilter)
    def mainSrc = "$project.projectDir/src/main/java"

    sourceDirectories.from = files([mainSrc])
    classDirectories.from = files([debugTree])

    executionData.from = fileTree(dir: project. buildDir, includes: [
    'jacoco/testDebugUnitTest.exec', 'outputs/code_coverage/debugAndroidTest/connected/**/*.ec'
    ])
}