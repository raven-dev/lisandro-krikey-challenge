# Krikey Android challenge

Tools/libraries used in this challenge:
  - Navigation Fragment (single activity)
  - ViewModel
  - LiveData
  - Coroutines
  - Lifecycle extensions
  - Retrofit
  - Gson
  - Glide

# About project:

  - Develop based on MVVM and repository patterns
  - Dependency injection with only Kotlin to show I understand the principle. That's why I didn't add any DI tool like Dagger2, Koin or Hilt.
  - Used some reactive programming with coroutines and livedata
  - Single activity / multiple fragment architecture


Also:
  - I using brand new coroutines implementation with retrofit with a central Api Handler to handle error about the current call or error from server. I.E: this class can handle and parse the error response for the server, so ViewModels and Views don't contain logic surrounding error messages. ViewModels and Views can focus on display.
  
# Resources
* [Cat list screen] - All cats list
* [Cat detail] - Selected cat screen
* [Share cat image url] - Share cat image url

   [Cat list screen]: <https://www.dropbox.com/s/w0s8709fvn8rjvm/1.png?dl=0>
   [Cat detail]: <https://www.dropbox.com/s/nasp9okvva2lffd/2.png?dl=0>
   [Share cat image url]: <https://www.dropbox.com/s/7b76hxuas9qvcs1/3.png?dl=0>

Also included a debug APK at the root for easy testing.