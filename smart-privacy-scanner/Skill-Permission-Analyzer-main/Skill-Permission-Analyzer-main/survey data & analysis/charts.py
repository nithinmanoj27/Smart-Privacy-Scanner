import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# Read the survey data from the CSV file
df = pd.read_csv('/Users/karnatikalyan/Documents/Privacy/Project/survery_results.csv')
df = df.drop([0, 1]).reset_index(drop=True)  # Drop unnecessary rows and reset the index


# Define sensitive permissions for each app
amazon_sensitive_permissions = ['Location', 'Access Files or Storage', 'Camera', 'Contacts', 'Microphone']
insta_sensitive_permissions = ['Location', 'Bluetooth', 'Camera', 'Contacts', 'Microphone', 'Calender', 'External Storage', 'Phone App(Call App)']
moodle_sensitive_permissions = ['Location', 'Camera', 'Contacts', 'Microphone', 'External Storage', 'Settings of Phone']

# Splitting multi-choice questions into individual columns
df['Q3'] = df['Q3'].apply(lambda x: x.split(','))
df['Q4'] = df['Q4'].apply(lambda x: x.split(','))
df['Q5'] = df['Q5'].apply(lambda x: x.split(','))

# Create separate dataframes for iPhone and Android users
iphone_df = df[df['Q2'] == 'Iphone']
android_df = df[df['Q2'] == 'Android']

# Counting occurrences of each option for Q3, Q4, and Q5 for iPhone and Android
q3_counts_iphone = iphone_df.explode('Q3')['Q3'].value_counts()
q4_counts_iphone = iphone_df.explode('Q4')['Q4'].value_counts()
q5_counts_iphone = iphone_df.explode('Q5')['Q5'].value_counts()

q3_counts_android = android_df.explode('Q3')['Q3'].value_counts()
q4_counts_android = android_df.explode('Q4')['Q4'].value_counts()
q5_counts_android = android_df.explode('Q5')['Q5'].value_counts()

# Define color mapping dictionaries for visualizations
color_mapping_amazon = {'Location': 'cornflowerblue', 'Access Files or Storage': 'darkorange', 'Camera': 'mediumseagreen',
                        'Contacts': 'lightcoral', 'Microphone': 'rebeccapurple'}

color_mapping_insta = {'Location': 'cornflowerblue', 'Bluetooth': 'darkorange', 'Camera': 'mediumseagreen',
                       'Contacts': 'lightcoral', 'Microphone': 'rebeccapurple', 'Calender': 'gold',
                       'External Storage': 'indianred', 'Phone App(Call App)': 'deepskyblue'}

color_mapping_moodle = {'Location': 'cornflowerblue', 'Camera': 'darkorange', 'Contacts': 'mediumseagreen',
                        'Microphone': 'lightcoral', 'External Storage': 'rebeccapurple', 'Settings of Phone': 'gold'}

# Visualize Q3 for iPhone and Android users accessing Amazon App
plt.figure(figsize=(15, 7))

plt.subplot(1, 2, 1)
q3_counts_iphone.reindex(amazon_sensitive_permissions).plot(kind='bar', color=[color_mapping_amazon[p] for p in amazon_sensitive_permissions])
plt.title('Q3 - Permissions accessed by Amazon App - iPhone')
plt.yticks(np.arange(0, q3_counts_iphone.max() + 1, 3))
plt.xlabel('Amazon Permissions') 

plt.subplot(1, 2, 2)
q3_counts_android.reindex(amazon_sensitive_permissions).plot(kind='bar', color=[color_mapping_amazon[p] for p in amazon_sensitive_permissions])
plt.title('Q3 - Permissions accessed by Amazon App - Android')
plt.yticks(np.arange(0, q3_counts_android.max() + 1, 3))
plt.xlabel('Amazon Permissions') 

plt.tight_layout()
plt.show()

# Visualize Q4 for iPhone and Android users accessing Instagram App
plt.figure(figsize=(15, 7))

plt.subplot(1, 2, 1)
q4_counts_iphone.reindex(insta_sensitive_permissions).plot(kind='bar', color=[color_mapping_insta[p] for p in insta_sensitive_permissions])
plt.title('Q4 - Permissions accessed by Instagram App - iPhone')
plt.yticks(np.arange(0, q4_counts_iphone.max() + 1, 3))
plt.xlabel('Instagram Permissions') 

plt.subplot(1, 2, 2)
q4_counts_android.reindex(insta_sensitive_permissions).plot(kind='bar', color=[color_mapping_insta[p] for p in insta_sensitive_permissions])
plt.title('Q4 - Permissions accessed by Instagram App - Android')
plt.yticks(np.arange(0, q4_counts_android.max() + 1, 3))
plt.xlabel('Instagram Permissions') 

plt.tight_layout()
plt.show()

# Visualize Q5 for iPhone and Android users accessing Moodle App
plt.figure(figsize=(15, 7))

plt.subplot(1, 2, 1)
q5_counts_iphone.reindex(moodle_sensitive_permissions).plot(kind='bar', color=[color_mapping_moodle[p] for p in moodle_sensitive_permissions])
plt.title('Q5 - Permissions accessed by Moodle App - iPhone')
plt.yticks(np.arange(0, q5_counts_iphone.max() + 1, 3))
plt.xlabel('Moodle Permissions') 

plt.subplot(1, 2, 2)
q5_counts_android.reindex(moodle_sensitive_permissions).plot(kind='bar', color=[color_mapping_moodle[p] for p in moodle_sensitive_permissions])
plt.title('Q5 - Permissions accessed by Moodle App - Android')
plt.yticks(np.arange(0, q5_counts_android.max() + 1, 3))
plt.xlabel('Moodle Permissions') 

plt.tight_layout()
plt.show()

# Create a DataFrame for Q3, Q4, and Q5 counts
q3_table = pd.DataFrame({'iPhone': q3_counts_iphone, 'Android': q3_counts_android})
q4_table = pd.DataFrame({'iPhone': q4_counts_iphone, 'Android': q4_counts_android})
q5_table = pd.DataFrame({'iPhone': q5_counts_iphone, 'Android': q5_counts_android})

# Display the tables
print("Table for Amazon:")
print(q3_table)

print("\nTable for Instagram:")
print(q4_table)

print("\nTable for Moodle:")
print(q5_table)

import seaborn as sns

# Combine the counts for Q3, Q4, and Q5 for iPhone and Android, including merging "Access Files or Storage" and "External Storage"
def combine_permissions_counts(q_counts):
    q_counts_combined = q_counts.copy()
    q_counts_combined['Access/External Storage'] = q_counts_combined.get('Access Files or Storage', 0) + q_counts_combined.get('External Storage', 0)
    q_counts_combined.drop(['Access Files or Storage', 'External Storage'], errors='ignore', inplace=True)
    return q_counts_combined

q3_counts_iphone_combined = combine_permissions_counts(q3_counts_iphone)
q4_counts_iphone_combined = combine_permissions_counts(q4_counts_iphone)
q5_counts_iphone_combined = combine_permissions_counts(q5_counts_iphone)

q3_counts_android_combined = combine_permissions_counts(q3_counts_android)
q4_counts_android_combined = combine_permissions_counts(q4_counts_android)
q5_counts_android_combined = combine_permissions_counts(q5_counts_android)

# Combine the counts for all permissions for iPhone and Android
combined_counts_iphone = q3_counts_iphone_combined.add(q4_counts_iphone_combined, fill_value=0).add(q5_counts_iphone_combined, fill_value=0)
combined_counts_android = q3_counts_android_combined.add(q4_counts_android_combined, fill_value=0).add(q5_counts_android_combined, fill_value=0)

# Create a DataFrame for combined counts
combined_table = pd.DataFrame({'iPhone': combined_counts_iphone, 'Android': combined_counts_android})

# Reset the index for the pair plot
combined_table = combined_table.reset_index()

# Melt the DataFrame for pair plot
melted_combined_table = pd.melt(combined_table, id_vars='index', var_name='Platform', value_name='Count')

# Define color mapping for pair plot
colors = {'iPhone': 'blue', 'Android': 'orange'}

# Create a paired bar plot
plt.figure(figsize=(15, 7))
sns.barplot(x='index', y='Count', hue='Platform', data=melted_combined_table, palette=colors)

plt.title('Combined Permissions accessed by iPhone and Android Users')
plt.xlabel('Permissions')
plt.ylabel('Count')
plt.xticks(rotation=45, ha='right')
plt.legend()

plt.show()
