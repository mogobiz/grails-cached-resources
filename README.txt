
This plugin calculates SHA256 hashes of your static resources and renames them to include this hash.

It then sets long term caching headers on the resources when rendering them.

Resources can be excluded via Ant-style expressions:

grails.cached.resources.excludes = [
    "*.png",
    "**/*.jpg"
]

Builds on the "Resources" framework plugin 

Todos:

* Make the "excludes" option accept closures in addition to Ant-style
  expressions, so that it can be determined at runtime. For example:

  cached.resources.excludes = [
	  '*.pdf',
	  'assets/',
	  { uri ->
	  	  return !uri.startsWith('catalogue')
	  }
  ]



