# Skill Permission Analyzer

## Overview
Welcome to the Skill Permission Analyzer, In today's digital era, mobile applications have become an integral part of our daily lives. However, concerns regarding user privacy have surged with the widespread use of these apps. Many applications collect vast amounts of user data without explicit consent, potentially compromising user privacy.

Our Android application serves as a solution to address these privacy concerns. It enables users to input the names of apps they use, fetches and analyzes the trackers and permissions requested by these apps, and provides insights into potential privacy risks associated with each app.

## Technology Stack
- **Platform**: Android
- **Programming Languages**: Java (for backend logic), XML (for app layout and UI design)
  
## Problem Statement
- Empower users to make informed decisions about app installations.
- Safeguard users from privacy-invasive apps.
- Raise awareness about mobile app-related privacy issues.

## Motivation
Our motivation stems from three key aspects:
1. **Informed Privacy Decisions**: We aim to assist users in understanding the data collected by apps, allowing them to make conscious choices about their privacy.
2. **User Protection**: Identifying and exposing apps employing excessive data collection or questionable tracking practices to help users avoid potential privacy risks.
3. **Privacy Awareness**: Educating users about potential privacy risks associated with mobile apps to encourage mindfulness and proactive measures for safeguarding privacy.

## Proposed Approach
### Exodus Privacy Analysis
- **Objectives**:
  - Understand Exodus's approach to tracker and permission detection.
  - Explore techniques for extracting app information and categorizing privacy risks.
- **Key Takeaways**:
  - Exodus employs a multifaceted approach to tracker analysis using heuristics and pattern matching.
  - Integrating Exodus API into our app allows for efficient retrieval and presentation of privacy risk insights.

### Survey and Reporting
- **Purpose**:
  - Gauge user awareness and preferences regarding app permissions.
  - Identify common concerns about sensitive permissions and comparative app analysis.
- **User Testing and Validation**:
  - Conducted diverse user testing sessions to evaluate usability and effectiveness.
  - Criteria include ease of use, clarity of insights, and overall user satisfaction.

## Findings
- **Exodus Insights**: Highlighted sensitive permissions accessed by apps like Amazon, Instagram, and Moodle, including location, camera, microphone, contacts, storage, and calling.
- **User Testing**: Received positive feedback on usability and identification of permissions, with suggestions for minor enhancements.

## Observation From the Survey
- Varied preferences between Android and iPhone users regarding permission grants.
- Common permissions granted across all apps (location, camera, microphone) and variable preferences for contacts and storage access.

## Limitations and Future Work
### Limitations
- **App Focus**: Analysis concentrated on a limited set of apps (Amazon, Instagram, Moodle).
- **Survey Diversity**: Lack of diverse participant profiles may limit broader applicability of findings.
- **Android Exclusivity**: App designed exclusively for Android, limiting iOS accessibility.
- **Limited Tracker Discussion**: The discussion on app trackers needs expansion for better user understanding.

### Future Works
- **Diversify App Focus**: Include a broader range of applications in studies.
- **Improve Survey Diversity**: Target a more diverse participant base for representative insights.
- **Cross-Platform Development**: Consider developing the app for iOS.
- **Integration with TrackerControl**: Enhance user privacy by integrating with privacy-focused tools like TrackerControl.

## App Screenshots
![q](https://github.com/vineeth-dasi/Skill_Permission_Analyzer/assets/36439540/bfbe18a0-a906-449c-9e89-d09af34a0e28)
![w](https://github.com/vineeth-dasi/Skill_Permission_Analyzer/assets/36439540/02af7a4f-02f8-41c5-9b04-eea4364cb061)

## Contributors
1. Akruti Sinha
2. [Vineeth Dasi](https://github.com/vineeth-dasi)
3. Kalyan Karnati

_Note: Please be advised that this project constitutes a component of an academic endeavor for CSC 533: Privacy in a Digital Age, completed during the Fall semester of 2023._
