# Cryptic_Android_Application

App Description: Secure Login and Data Encryption

The "Secure Login and Data Encryption" app is a robust Android application designed to provide users with a secure and seamless login experience, along with powerful data encryption functionalities. The app utilizes Firebase Authentication and Firestore for user registration and login, ensuring that user data is protected and managed with the utmost security.

Technologies Used:  JAVA, Firebase

Key Features:

1.	Splash Screen for Smooth Onboarding: The app features an attractive and informative splash screen that welcomes users upon launch. It efficiently checks the user's authentication status and redirects them to the appropriate screen based on their login status, offering a smooth and intuitive onboarding experience.
2.	User Registration and Login: Users can effortlessly create new accounts by providing their full name, email, password, and phone number through the intuitive registration interface. Firebase Authentication verifies the user credentials, ensuring a secure login process. In case of successful login, users gain access to the app's main features.
3.	Main Dashboard (MainActivity): The main dashboard serves as the home screen for authenticated users. It offers easy access to essential functionalities, providing a seamless user experience.
4.	Data Encryption (Encoder): The app empowers users with advanced data encryption capabilities, employing the AES algorithm in CBC mode with PKCS5Padding. Through the "Encoder" activity, users can encrypt sensitive data, such as messages or files, using a strong and secure password. The encrypted data is then safely stored and transmitted.
5.	Data Decryption (Decoder): The "Decoder" activity allows users to decrypt previously encrypted data, ensuring seamless data retrieval. The AES decryption process utilizes the same secure password used during encryption, providing a secure and straightforward decryption process.
6.	Clipboard Integration: To enhance user convenience, the app integrates with the device clipboard. Users can effortlessly copy encrypted or decrypted data to the clipboard for further use or sharing with others.
7.	Data Storage with Firestore: The app utilizes Firebase Firestore as a scalable and secure backend infrastructure to store user data and authentication records. This ensures data integrity, availability, and confidentiality, adhering to industry best practices.
8.	Email Verification for Enhanced Security: To bolster account security, the app implements email verification for newly registered users. Upon registration, users receive a verification email, safeguarding their accounts from unauthorized access and providing an additional layer of protection.
