
enablePlugins(ScalaJSBundlerPlugin, GitHubPagesPlugin)

lazy val GitHubUsername = "Kevin-Lee"
lazy val GitHubRepoName = "just-toss"

name := GitHubRepoName

scalaVersion := "2.13.3"

gitHubPagesOrgName := GitHubUsername
gitHubPagesRepoName := GitHubRepoName
gitHubPagesSiteDir := baseDirectory.value / "build"

npmDevDependencies in Compile += "html-webpack-plugin" -> "4.3.0"
npmDevDependencies in Compile += "copy-webpack-plugin" -> "5.1.1"
npmDevDependencies in Compile += "webpack-merge" -> "4.2.2"

libraryDependencies += "io.lemonlabs" %%% "scala-uri" % "3.0.0"

scalacOptions += "-Ymacro-annotations"

version in webpack := "4.43.0"
version in startWebpackDevServer:= "3.11.0"

webpackResources := baseDirectory.value / "webpack" * "*"

webpackConfigFile in fastOptJS := Some(baseDirectory.value / "webpack" / "webpack-fastopt.config.js")
webpackConfigFile in fullOptJS := Some(baseDirectory.value / "webpack" / "webpack-opt.config.js")
webpackConfigFile in Test := Some(baseDirectory.value / "webpack" / "webpack-core.config.js")

webpackDevServerExtraArgs in fastOptJS := Seq("--inline", "--hot")
webpackBundlingMode in fastOptJS := BundlingMode.LibraryOnly()

requireJsDomEnv in Test := true

addCommandAlias("dev", ";fastOptJS::startWebpackDevServer;~fastOptJS")

addCommandAlias("devBuild", ";fastOptJS::startWebpackDevServer;fastOptJS")

addCommandAlias("build", "fullOptJS::webpack")
