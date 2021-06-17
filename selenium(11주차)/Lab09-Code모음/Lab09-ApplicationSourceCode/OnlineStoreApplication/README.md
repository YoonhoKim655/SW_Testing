# README

This README would normally document whatever steps are necessary to get the
application up and running.

Things you may want to cover:

* Ruby version

* System dependencies

* Configuration

* Database creation

* Database initialization

* How to run the test suite

* Services (job queues, cache servers, search engines, etc.)

* Deployment instructions

* ...

# Changelog
Date: 14.03.19 
Author: Ezequiel Scott

I changed the file `config\environments\production.rb`, line 26, from
`config.assets.compile = true`
to
`config.assets.compile = false`

in order to deactivate the security vulnerability checkings. https://blog.heroku.com/rails-asset-pipeline-vulnerability