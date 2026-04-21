Internship assignment 
This is a simple REST API where you can create posts, like them, comment on them, and check how “viral” they are based on interactions.
🌐 Base URL
http://localhost:8080/api
🧪 How it works (quick idea)
Likes add +20 points
Comments add +50 points
You can track total “virality score” of each post
📝 1. Create a Post
Create a new post for a user.
Request  POST http://localhost:8080/api/posts
Body
{
  "authorId": 1,
  "content": "My first viral post"
}
What you get back
A post object
Most importantly: the post id (you’ll use this everywhere next)
❤️ 2. Like a Post
Like a post and boost its score.
Request 
POST /posts/{postId}/like
Example:
POSt /posts/1/like
What happens
Every like adds +20 points
You can hit this multiple times (yes, spam likes work 😄)
💬 3. Add a Comment
Drop a comment on a post.
Request 
POST /posts/{postId}/comments
Body
{
  "authorId": 2,
  "content": "Nice post!",
  "depthLevel": 1
}
What happens
Each comment adds +50 points
So comments are more powerful than likes
📊 4. Check Virality Score
See how viral your post is.
Request
GET /posts/{postId}/score
Example:
GET /posts/1/score
Response
{
  "postId": 1,
  "score": 90
}
⚡ Scoring Summary
👍 Like → +20
💬 Comment → +50
Simple enough.
🔁 Example Flow
Create a post → get id = 1
Like it 2 times → +40
Add 1 comment → +50
Final score → 90
