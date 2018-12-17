# Jokes
You will have to create two screens based on the below mentioned APIs
( Refer to the documentation here : http://www.icndb.com/api/ ).
The app have two screens:
1. Show the users categories fetched by this API:
## http://api.icndb.com/categories
Each of these Categories should be represented as a Fragment in a Viewpager.

2. On Clicking a button a Detail Screen should be loading where the data should be loaded
from this API :
## http://api.icndb.com/jokes/random/10?limitTo=[CATEGORY_NAME]
the CATEGORY_NAME is here is the one user selected in the previous screen.
Based on the results of the API, show a list of the items (Jokes returned by the API)

# Important : Use Android Architecture components (LiveData, ViewModel) and Kotlin to implement
the project.
