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
  - Dependency injection as "naked way" just to let you know I understand the principle. That's why I didn't add any DI tool like Dagger2, Koin or Hilt.
  - Making some reactive programming with coroutines and livedata
  - Single Activity "architecture" (just for fun)


Also:
  - I using brand new coroutines implementation with retrofit with a centralice Api Handler to handle error about the current call or error from server. I.E: this cclass can handle and parse the error response for te server, so viewmodel or ui doesn't care about how get the error message. Just has to display it.
  
#Resources
   [Cat list screen]: <https://www.dropbox.com/s/w0s8709fvn8rjvm/1.png?dl=0>
   [Cat detail]: <https://www.dropbox.com/s/nasp9okvva2lffd/2.png?dl=0>
   [Share cat image url]: <https://www.dropbox.com/s/7b76hxuas9qvcs1/3.png?dl=0>

At the root of this you will find the builded apk to test it.